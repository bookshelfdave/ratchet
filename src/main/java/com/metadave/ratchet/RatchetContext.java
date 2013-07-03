package com.metadave.ratchet;


import com.basho.riak.client.IRiakClient;
import com.basho.riak.client.RiakFactory;

public class RatchetContext {
    IRiakClient riak = null;
    public void connect() throws Exception {
        riak = RiakFactory.pbcClient("localhost", 10017);
    }

    public IRiakClient getDefaultRiakClient() {
        return riak;
    }
}
