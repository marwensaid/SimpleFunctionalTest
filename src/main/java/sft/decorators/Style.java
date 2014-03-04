/*******************************************************************************
 * Copyright (c) 2013, 2014 Sylvain Lézier.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sylvain Lézier - initial implementation
 *******************************************************************************/
package sft.decorators;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sft.UseCase;

public class Style implements Decorator {
    private String[] styles;

    @Override
    public Decorator withParameters(String... parameters) {
        if(parameters == null || parameters.length == 0 ){
            throw new RuntimeException("Style decorator need one or more parameters");
        }
        styles = parameters;
        return this;
    }

    @Override
    public String applyOnUseCase(UseCase useCase,String result){
        return addStyleToElementWithClass(result, ".useCase");
    }

    private String addStyleToElementWithClass(String result, String cssQuery) {
        final Document parse = Jsoup.parse(result);

        final Elements elements = parse.select(cssQuery);
        if(elements == null || elements.size()==0){
            throw new RuntimeException("The decorator "+this.getClass().getCanonicalName()+" need class "+cssQuery+" in generated html to be usable.");
        }
        for (String style : styles) {
            elements.addClass(style);
        }
        return parse.toString();
    }


    @Override
    public String applyOnScenario(String result){
        return addStyleToElementWithClass(result, ".scenario");
    }

    @Override
    public String applyOnFixture(String result){
        return addStyleToElementWithClass(result, ".instruction");
    }
}