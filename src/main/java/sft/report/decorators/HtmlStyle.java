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
package sft.report.decorators;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sft.DefaultConfiguration;
import sft.decorators.Decorator;
import sft.decorators.Style;
import sft.result.FixtureCallResult;
import sft.result.UseCaseResult;

import java.util.List;

public class HtmlStyle extends Style {

    public HtmlStyle(DefaultConfiguration configuration, String... parameters) {
        super(configuration, parameters);
    }

    public HtmlStyle(Decorator decorator) {
        super(decorator);
    }

    @Override
    public String applyOnUseCase(UseCaseResult useCaseResult,String result){
        return addStyleToElementWithClass(result, ".useCase");
    }

    protected String[] getStyles(){
        if(parameters == null || parameters.length == 0 ){
            throw new RuntimeException("Style decorator need one or more parameters");
        }
        return parameters;
    }

    private String addStyleToElementWithClass(String result, String cssQuery) {
        final Document parse = Jsoup.parse(result);

        final Elements elements = parse.select(cssQuery);
        if(elements == null || elements.size()==0){
            throw new RuntimeException("The decorator "+this.getClass().getCanonicalName()+" need class "+cssQuery+" in generated html to be usable.");
        }
        for (String style : getStyles()) {
            elements.addClass(style);
        }
        return parse.toString();
    }

     @Override
    public String applyOnScenario(String result){
        return addStyleToElementWithClass(result, ".scenario");
    }

    @Override
    public String applyOnFixtures(List<String> fixtures, List<FixtureCallResult> fixtureCallResuts){
        String result = "";
        for (String fixture : fixtures) {
            result+= addStyleToElementWithClass(fixture, ".instruction");
        }
        return result;
    }
}