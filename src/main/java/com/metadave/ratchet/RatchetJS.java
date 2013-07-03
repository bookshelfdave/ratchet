package com.metadave.ratchet;

import com.metadave.ratchet.scripting.RatchetDB;
import org.apache.commons.io.IOUtils;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import java.io.InputStream;
import java.io.PrintStream;
public class RatchetJS {
    Scriptable jsscope = null;

    public RatchetJS(RatchetContext ctx, PrintStream out, PrintStream err) {
        Context cx = Context.enter();
        jsscope = cx.initStandardObjects();
        Object wrappedOut = Context.javaToJS(out, jsscope);
        ScriptableObject.putProperty(jsscope, "out", wrappedOut);

        Object wrappedErr = Context.javaToJS(err, jsscope);
        ScriptableObject.putProperty(jsscope, "err", wrappedErr);

        RatchetDB db = new RatchetDB(jsscope, ctx);
        Object wrappedDb = Context.javaToJS(db, jsscope);
        ScriptableObject.putProperty(jsscope, "riak", wrappedDb);

        Context.exit();
    }

    public String loadScript(String filename) throws Exception {
        InputStream is = getClass().getResourceAsStream(filename);
        return IOUtils.toString(is);
    }

    public Object evalScript(String script) {
        Context cx = Context.enter();
        try {
            Object o = cx.evaluateString(jsscope, script, "<ratchet>", 1, null);
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Context.exit();
        }
        return null;
    }
}
