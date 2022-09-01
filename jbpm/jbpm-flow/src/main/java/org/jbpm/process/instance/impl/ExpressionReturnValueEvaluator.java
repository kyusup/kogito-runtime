/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jbpm.process.instance.impl;

import org.kie.kogito.internal.process.runtime.KogitoProcessContext;
import org.kie.kogito.process.expr.Expression;
import org.kie.kogito.process.expr.ExpressionHandlerFactory;

public class ExpressionReturnValueEvaluator implements ReturnValueEvaluator {
    private Expression expression;
    private String rootName;
    private Class<?> returnType;

    public ExpressionReturnValueEvaluator(String lang, String expression, String rootName) {
        this(lang, expression, rootName, Boolean.class);
    }

    public ExpressionReturnValueEvaluator(String lang, String expression, String rootName, Class<?> returnType) {
        this.expression = ExpressionHandlerFactory.get(lang, expression);
        this.rootName = rootName;
        this.returnType = returnType;
    }

    @Override
    public Object evaluate(KogitoProcessContext processContext) throws Exception {
        return expression.eval(processContext.getVariable(rootName), returnType, processContext);
    }
}