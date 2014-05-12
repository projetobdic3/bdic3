package br.com.ita.bdic3.tags;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

@SuppressWarnings("serial")
public class JSONTag extends RequestContextAwareTag {

    private Object val;

    @Override
    protected int doStartTagInternal() throws Exception {
        try {
            JspWriter out = pageContext.getOut();
            ObjectMapper mapper = getRequestContext().getWebApplicationContext().getBean("objectMapper", ObjectMapper.class);
            out.write(mapper.writeValueAsString(val));
        } catch (Exception ex) {
            throw new JspException(ex);
        }
        return EVAL_PAGE;
    }

    public void setVal(Object val) {
        this.val = val;
    }

}
