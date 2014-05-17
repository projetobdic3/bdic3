package br.ita.bdic3.util;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.*;

public class ReloadableMessageSource extends ReloadableResourceBundleMessageSource {

    public Properties getProperties(Locale locale) {
        return super.getMergedProperties(locale).getProperties();
    }
}
