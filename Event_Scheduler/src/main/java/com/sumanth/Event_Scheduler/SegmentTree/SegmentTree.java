package com.sumanth.Event_Scheduler.SegmentTree;

public class SegmentTree {

    // 24 hours ==> 24*60=1440
    // segment tree needs 4 *N
    boolean[] sg=new boolean[4*1440];
    public SegmentTree(){

    }
   public void build(int l,int r,int index){
        System.out.println(l+"-"+r+" has a value"+sg[index]);
        if(l==r){
            return ;
        }
        int mid=(l+r)/2;
        build(l,mid,2*index+1);
        build(mid+1,r,2*index+2);

    }
    public boolean checkEventCanHappen(int l,int r,int start,int end,int index){
        // not overlap
        //System.out.println("from "+l+"-"+r+"has a value"+sg[index]);
        if(end<l || start>r){
            return true;
        }

        // completely overlapped
        else if(start<=l && r<=end){
            return !sg[index];
        }
        if(sg[index]){
            return false;
        }
        // partial Overlaped
        int mid=(l+r)/2;
        boolean left=checkEventCanHappen(l,mid,start,end,2*index+1);
        boolean right=checkEventCanHappen(mid+1,r,start,end,2*index+2);
//        System.out.println("from "+l+"-"+r+"has a value"+sg[index]);
        return (left && right);
    }
    public boolean update(int l,int r,int start,int end,int index){
        //System.out.println("from "+l+"-"+r+"has a value"+sg[index]);
        if(end<l || start>r){
            return false;
        }
        // completely overlapped
        else if(start<=l && r<=end){
            sg[index]=true;
            return sg[index];
        }
        // partial Overlaped
        int mid=(l+r)/2;
        boolean left=update(l,mid,start,end,2*index+1);
        boolean right=update(mid+1,r,start,end,2*index+2);
        sg[index]=(left && right);

        return sg[index];
    }
}
