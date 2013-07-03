package com.metadave.ratchet.scripting;


import com.basho.riak.client.RiakException;
import com.basho.riak.client.bucket.Bucket;
import com.metadave.ratchet.RatchetContext;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import java.util.HashSet;
import java.util.Set;

public class RatchetDB extends RatchetScriptable {
    public RatchetDB(Scriptable jsscope, RatchetContext ctx) {
        super(jsscope, ctx);
    }

    public void connect() {
        try {
            // skip IP/port for now, just playing around
            ctx.connect();
        } catch (Exception e) {
            System.out.println("kittens have died :-(");
            e.printStackTrace();
        }
    }

    public void connect(String hostname, int port) {
        try {
            // skip IP/port for now, just playing around
            ctx.connect();
        } catch (Exception e) {
            System.out.println("kittens have died :-(");
            e.printStackTrace();
        }
    }

    public RatchetBucket bucket(String name) {
        try {
            //Context cx = Context.enter();
            Bucket b = ctx.getDefaultRiakClient().fetchBucket(name).execute();
            RatchetBucket ratchetBucket = new RatchetBucket(jsscope, ctx, b);
            return ratchetBucket;
            //Object wrappedDb = Context.javaToJS(b, jsscope);
            //ScriptableObject.putProperty(jsscope, "riak", wrappedDb);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Context.exit();
        }
        return null;
    }

    public Set<String> listBuckets() {
        Set<String> buckets = new HashSet<String>();
        try {
            buckets = ctx.getDefaultRiakClient().listBuckets();
        } catch (RiakException e) {
            e.printStackTrace();
        }
        return buckets;
    }
}
