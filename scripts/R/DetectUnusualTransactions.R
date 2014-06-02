# Necessario instalar o pacote chron com o comando abaixo
# install.packages('chron')
library(chron)
library(lattice)

# Lendo o CSV com o valor das transações
data <- read.csv("http://localhost.localdomain:50075/browseBlock.jsp?blockId=-1596810953341040685&blockSize=75384910&genstamp=355428&filename=%2Fuser%2Fhive%2Fwarehouse%2Fbdic3.db%2Ftransacao%2Ftransacao.csv&datanodePort=50010&namenodeInfoPort=50070&nnaddr=localhost:8020")

# Calculando as estatísticas da amostra
value_summary <- summary(data$tra_valor_total)
mean_value <- value_summary["Mean"]
first_quartile <- value_summary["1st Qu."]
median_value <- value_summary["Median"]
third_quartile <- value_summary["3rd Qu."]
upper_limit <- third_quartile + 1.5* (third_quartile - first_quartile)

# Plotando o histograma dos valores das transações
hist(data$tra_valor_total, breaks=10, main = "Histograma dos valores das transações", xlab="Valor das transações")
abline(v=median_value, col = "blue")
abline(v=upper_limit, col = "red")

#Plotando o boxplot dos valores para visualizar os outliers
boxplot(data$tra_valor_total, main="Boxplot dos valores das transações (outliers em destaque)", ylab="Valor das transações" )


# Tratando a data/hora da transação
dtparts <- t(as.data.frame(strsplit(as.character(data$tra_data)," ")))
row.names(dtparts) <- NULL
thedatatimes <- chron(dates=dtparts[,1],times=dtparts[,2], format=c('d/m/y','h:m:s'))
thetimes <- chron(times=dtparts[,2], format=c('h:m:s'))

#Plotando o gráfico das transações em função do horário (do último semestre)
xyplot(data$tra_valor_total ~ thetimes, xlab="Horário", 
       ylab="Valor da Transação", main="Transações por horário no mês 02/2014",
       panel = function(x, y) {
         panel.xyplot(x, y)
         panel.abline(mean_value, col = "blue")
         panel.abline(upper_limit, col = "red")
       },)

#Plotando o gráfico das transações em função da data e hora (do último semestre)
xyplot(data$tra_valor_total ~ thedatatimes, xlab="Data", 
       ylab="Valor da Transação", main="Transações por dia no mês 02/2014",
       panel = function(x, y) {
         panel.xyplot(x, y)
         panel.abline(mean_value, col = "blue")
         panel.abline(upper_limit, col = "red")
       },)

suspect_transactions <- data[data$tra_valor_total>3000,]
sprintf("Transações suspeitas (maiores que %f)", upper_limit)
print(suspect_transactions)