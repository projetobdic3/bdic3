# necessario instalar o pacote chron com o comando abaixo
# install.packages ( "chron" )
library(chron)
library(lattice)

# necessario instalar o pacote RODBC com o comando abaixo
#install.packages ( "RODBC" )
library (RODBC)

# conecta o ODBC do Impala
conn <- odbcConnect ( "Impala" )

# abre o banco
result <- sqlQuery ( conn, "use BDIC3" )

args <- commandArgs ( trailingOnly = TRUE )
# print ( args )

cliID <- as.integer ( args [ 1 ] )
rm ( args )

# seleciona as transações do cliente
strQuery <- sprintf ( "select tra_id, tra_tipo, tra_total, tra_data, tra_hora, etb_id, cli_id, loc_id from transacao where cli_id = %d", cliID )
# strQuery

result <- sqlQuery ( conn, strQuery )
result

# calculando as estatísticas da amostra
value_summary <- summary ( result$tra_total )

mean_value <- value_summary [ "Mean" ]
sprintf ( "mean_value: %f", mean_value )

first_quartile <- value_summary [ "1st Qu." ]
sprintf ( "first_quartile: %f", first_quartile )

median_value <- value_summary [ "Median" ]
sprintf ( "median_value: %f", median_value )

third_quartile <- value_summary [ "3rd Qu." ]
sprintf ( "third_quartile: %f", third_quartile )

upper_limit <- third_quartile + 0.1 * ( third_quartile - first_quartile )
sprintf ( "upper_limit: %f", upper_limit )

# plota o histograma dos valores das transações
png ( "/home/cloudera/git/bdic3/bdic3/bdic3-webapp/src/main/webapp/histograma.png" )
hist ( result$tra_total, breaks=10, main = "Histograma dos valores das transações", xlab="Valor das transações" )
abline ( v=median_value, col = "blue" )
abline ( v=upper_limit, col = "red" )

# plota o boxplot dos valores para visualizar os outliers
# png ( "boxplot.png" )
# boxplot ( result$tra_total, main="Boxplot dos valores das transações (outliers em destaque)", ylab="Valor das transações" )

# trata a data/hora da transação
dtparts <- t ( as.data.frame ( strsplit ( as.character ( result$tra_data ), " " )))

row.names ( dtparts ) <- NULL

thedatatimes <- chron ( dates=dtparts[,1], format=c('d/m/y'))

# plota o gráfico das transações em função da data
png ( "/home/cloudera/git/bdic3/bdic3/bdic3-webapp/src/main/webapp/transacoes.png" )
xyplot ( result$tra_total ~ thedatatimes, xlab="Data", 
         ylab="Valor da Transação", main="Transações por dia no mês",
         panel = function ( x, y ) {
         panel.xyplot ( x, y )
         panel.abline ( mean_value, col = "blue" )
         panel.abline ( upper_limit, col = "red" )
       },)

# seleciona as transações suspeitas
suspect_transactions <- result [ result$tra_total > upper_limit, ]

# exibe as transações suspeitas
sprintf ( "Transações suspeitas (maiores que %f)", upper_limit )
print ( suspect_transactions )
