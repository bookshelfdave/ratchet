package com.metadave.ratchet.scripting;

import com.basho.riak.client.IRiakObject;
import com.metadave.ratchet.RatchetContext;
import org.mozilla.javascript.Scriptable;

public class RatchetRiakObject extends RatchetScriptable {
    IRiakObject obj;

    public RatchetRiakObject(Scriptable jsscope, RatchetContext ctx, IRiakObject obj) {
        super(jsscope, ctx);
        this.obj = obj;
    }

    public String toString() {
        return obj.getValueAsString();
    }
}
