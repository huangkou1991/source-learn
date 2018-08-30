/*
 * Copyright (c) 2018 Uber Technologies, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.learn.springbootguava.meta;

public class ClassAndMethod {
    private final String className;
    private final String methodName;

    public ClassAndMethod(String className, String methodName) {
        if (className == null) {
            throw new NullPointerException("className");
        }

        if (methodName == null) {
            throw new NullPointerException("methodName");
        }

        this.className = className;
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassAndMethod that = (ClassAndMethod) o;

        if (!className.equals(that.className)) return false;
        return methodName.equals(that.methodName);
    }

    @Override
    public int hashCode() {
        int result = className.hashCode();
        result = 31 * result + methodName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return className + '.' + methodName;
    }
}
