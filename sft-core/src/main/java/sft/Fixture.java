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
package sft;

import sft.decorators.Decorator;
import sft.decorators.DecoratorExtractor;
import sft.decorators.NullDecorator;
import sft.javalang.JavaToHumanTranslator;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Fixture {

    public final Method method;
    public final Decorator decorator;
    private final JavaToHumanTranslator javaToHumanTranslator = new JavaToHumanTranslator();
    public ArrayList<String> parametersName = new ArrayList<String>();

    public Fixture(Method method,DefaultConfiguration configuration) throws Exception {
        this.method = method;
        this.decorator = DecoratorExtractor.getDecorator(configuration, method.getDeclaredAnnotations());
    }

    public String getText() {
        return javaToHumanTranslator.humanize(method);
    }

}
