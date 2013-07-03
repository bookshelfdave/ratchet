package com.metadave.ratchet.scripting;


import com.basho.riak.client.IRiakObject;
import com.basho.riak.client.RiakException;
import com.basho.riak.client.RiakRetryFailedException;
import com.basho.riak.client.bucket.Bucket;
import com.basho.riak.client.bucket.RiakBucket;
import com.metadave.ratchet.RatchetContext;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.Scriptable;

import java.util.ArrayList;
import java.util.List;

public class RatchetBucket extends RatchetScriptable {
    private Bucket bucket;

    public RatchetBucket(Scriptable jsscope, RatchetContext ctx, Bucket bucket) {
        super(jsscope, ctx);
        this.bucket = bucket;
    }

    public List<String> listKeys() {
        List<String> allKeys = new ArrayList<String>();
        try {
            for(String k : bucket.keys()) {
                allKeys.add(k);
            }
        return allKeys;
        } catch (RiakException e) {
            e.printStackTrace();
        }
        return allKeys;
    }

    public RatchetRiakObject find(String key) {
        try {
            return new RatchetRiakObject(jsscope, ctx, bucket.fetch(key).execute());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public RatchetRiakObject find(String key, Object options) {
        try {
            NativeObject opts = (NativeObject)options;
            Context.enter();
            System.out.println(opts.keySet());
            Context.exit();
            return new RatchetRiakObject(jsscope, ctx, bucket.fetch(key).execute());
        } catch (RiakRetryFailedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String toString() {
        return bucket.getName();
    }
}
