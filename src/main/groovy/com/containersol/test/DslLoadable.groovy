package com.containersol.test;

class DslLoadable {

    def delegateTo(Object obj, Closure cl) {
        def code = cl.rehydrate(obj, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }

    def methodMissing(String methodName, args) {
        throw new MissingPropertyException("Block '" + methodName + "' not supported");
    }

}
