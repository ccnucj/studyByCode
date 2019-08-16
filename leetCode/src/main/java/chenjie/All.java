package chenjie;

import org.junit.Test;

public class All {

    /**
     * 构建除本身以外的乘积数组
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] res = new int[length];
        for(int i=0,product=1; i<length;product*=nums[i],i++) {
            res[i]=product;
        }
        for(int i=length-1,product=1; i>=0;product*=nums[i],i--) {
            res[i]*=product;
        }
        return res;
    }


    @Test
    public void testProductExceptSelf() {
        int[] nums = {1,2,3,4};
        int[] res = productExceptSelf(nums);
        System.out.println(res);
    }
}
