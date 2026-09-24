
public class Solution {
    public static int search(int num[],int key){
        int start=0,end=num.length-1;

        while(start<=end){
            int mid=(start+end)/2;

            if(num[mid]==key){
                return mid;
            }
            if(num[mid]<key){
                start=mid+1;
            }
            else{
                end=mid-1;
            }
        }
        return -1;
    }
    public static void main(String[] args){
        int num[]={4,6,8,10,11,90,110};
        int key=6;

        System.out.println("index for key is: " +search(num,key));
    }
}