package main;

import java.util.Arrays;

public class Story {
    
    public void storySequence() {

        int storyA[] = new int[7];
        Arrays.fill(storyA, 100);

        for (int i = 0, n = storyA.length; i < n; i++) {
            System.out.println(storyA[i]);
        }
        System.out.println();
        Arrays.fill(storyA, 3, 6, 10);

        for(int i = 0, n = storyA.length; i < n; i++) {
            System.out.println(storyA[i]);
            break;
        }

        
}
}
