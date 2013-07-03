package com.metadave.ratchet.scripting;

import com.metadave.ratchet.RatchetContext;
import org.mozilla.javascript.Scriptable;

public class RatchetScriptable {
    RatchetContext ctx;
    Scriptable jsscope;

    public RatchetScriptable(Scriptable jsscope, RatchetContext ctx) {
        this.ctx = ctx;
        this.jsscope = jsscope;
    }
}
