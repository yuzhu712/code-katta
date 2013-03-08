package com.ashishpaliwal.codekatta.algo1;

/**
 *
 */
public class QuickFindUF {

    private int[] ids;

    public QuickFindUF(int count) {
        ids = new int[count];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = i;
        }
    }

    public boolean isConnected(int p, int q) {
        return ids[p] == ids[q];
    }

    public void union(int p, int q) {
        int pid = ids[p];
        int qid = ids[q];

        for (int i = 0; i < ids.length; i++) {
            if(ids[i] == pid) {
                ids[i] = qid;
            }
            
        }
    }
}
