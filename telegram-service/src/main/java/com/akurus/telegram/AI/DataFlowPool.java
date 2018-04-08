package com.akurus.telegram.AI;

/**
 * Created by golizar on 24.12.17.
 */
public class DataFlowPool {
    private static DataFlow POLL = new DataFlow();

    public static DataFlow getConnection(){
        return POLL;
    }
}
