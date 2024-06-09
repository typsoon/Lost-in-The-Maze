package com.bksgames.game.core.boards;

import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.core.utils.Point;

import java.util.*;

/**
 * Class with useful functions when generating board
 * @author riper
 */
class BoardGenerationUtils {
    static final private Random rng = new Random();
    static final private int INFINITY = 10000;
    static final private int MAX_RANDOM = 50;

    @SuppressWarnings("SameParameterValue")
    static void randomPath(Point start, Point dest, int c, int[][]grid, int width, int height) {
       int[][]workGrid = new int[width][height];
       for(int y=0;y<height;y++)
       {
           for(int x=0;x<width;x++)
           {
               if(grid[x][y]==0 && check(grid,width,height,x+1,y,c) && check(grid,width,height,x-1,y,c)
                       && check(grid,width,height,x,y+1,c) && check(grid,width,height,x,y-1,c)
               )
                   workGrid[x][y]=rng.nextInt(MAX_RANDOM);
               else
                   workGrid[x][y]=INFINITY;
           }
       }
        int[][]lengthGrid = new int[width][height];
        int[][]prevGrid = new int[width][height];
        for(int y=0;y<height;y++)
            for(int x=0;x<width;x++)
                lengthGrid[x][y]=Integer.MAX_VALUE;
        lengthGrid[start.x][start.y]=1;
        PriorityQueue<Node> que = new PriorityQueue<>();
        que.add(new Node(start.x,start.y,0));
        while (!que.isEmpty()) {
            Node act = que.poll();
            if (act.l > lengthGrid[act.x][act.y])
                continue;
            if (act.x - 1 >= 0 && act.l + workGrid[act.x - 1][act.y] < lengthGrid[act.x - 1][act.y]) {
                lengthGrid[act.x - 1][act.y] = act.l + workGrid[act.x - 1][act.y];
                que.add(new Node(act.x - 1, act.y, lengthGrid[act.x - 1][act.y]));
                prevGrid[act.x - 1][act.y] = 1;
            }
            if (act.x + 1 < width && act.l + workGrid[act.x + 1][act.y] < lengthGrid[act.x + 1][act.y]) {
                lengthGrid[act.x + 1][act.y] = act.l + workGrid[act.x + 1][act.y];
                que.add(new Node(act.x + 1, act.y, lengthGrid[act.x + 1][act.y]));
                prevGrid[act.x + 1][act.y] = 2;
            }
            if (act.y - 1 >= 0 && act.l + workGrid[act.x][act.y - 1] < lengthGrid[act.x][act.y - 1]) {
                lengthGrid[act.x][act.y - 1] = act.l + workGrid[act.x][act.y - 1];
                que.add(new Node(act.x, act.y - 1, lengthGrid[act.x][act.y - 1]));
                prevGrid[act.x][act.y - 1] = 3;
            }
            if (act.y + 1 < height && act.l + workGrid[act.x][act.y + 1] < lengthGrid[act.x][act.y + 1]) {
                lengthGrid[act.x][act.y + 1] = act.l + workGrid[act.x][act.y + 1];
                que.add(new Node(act.x, act.y + 1, lengthGrid[act.x][act.y + 1]));
                prevGrid[act.x][act.y + 1] = 4;
            }
        }
        grid[start.x][start.y] = c;
        createPath(dest, grid, prevGrid, c);
    }

    @SuppressWarnings("SameParameterValue")
    static void generateRest(int [][]grid,int width,int height, int c){
        ArrayList<Point> fields = new ArrayList<>();
        for(int y=0;y<height;y++) {
            for(int x=0;x<width;x++){
                if(grid[x][y]==c)
                    fields.add(new Point(x,y));
            }
        }
        ArrayList<Integer> moves = new ArrayList<>();
        while(!fields.isEmpty())
        {
            Point act = fields.remove(rng.nextInt(fields.size()));
            moves.clear();
            if(act.x-1>=0 && grid[act.x-1][act.y] ==0
                    && check(grid,width,height, act.x-2, act.y,c) && check(grid,width,height, act.x-1, act.y+1,c)
                    && check(grid,width,height, act.x-1, act.y-1,c)
            ){
                moves.add(1);
            }
            if(act.x+1<width && grid[act.x+1][act.y] ==0
                    && check(grid,width,height, act.x+2, act.y,c) && check(grid,width,height, act.x+1, act.y+1,c)
                    && check(grid,width,height, act.x+1, act.y-1,c)
            ){
                moves.add(2);
            }
            if(act.y-1>=0 && grid[act.x][act.y-1] ==0
                    && check(grid,width,height, act.x, act.y-2,c) && check(grid,width,height, act.x-1, act.y-1,c)
                    && check(grid,width,height, act.x+1, act.y-1,c)
            ){
                moves.add(3);
            }
            if(act.y+1<height && grid[act.x][act.y+1] ==0
                    && check(grid,width,height, act.x, act.y+2,c) && check(grid,width,height, act.x-1, act.y+1,c)
                    && check(grid,width,height, act.x+1, act.y+1,c)
            ){
                moves.add(4);
            }
            if(moves.isEmpty())
                continue;
            if(moves.size()>1)
                fields.add(act);
            Integer chosen = moves.get(rng.nextInt(moves.size()));
            if(chosen==1){
                grid[act.x-1][act.y]=c;
                fields.add(new Point(act.x-1, act.y));
            }else if(chosen==2){
                grid[act.x+1][act.y]=c;
                fields.add(new Point(act.x+1, act.y));
            } else if (chosen==3) {
                grid[act.x][act.y-1]=c;
                fields.add(new Point(act.x, act.y-1));
            }else {
                grid[act.x][act.y+1]=c;
                fields.add(new Point(act.x, act.y+1));
            }
        }
    }
    private static void createPath(Point dest, int[][] grid, int[][] prevGrid,int c) {
        Point act = new Point(dest);
        grid[act.x][act.y]=c;
        while(prevGrid[act.x][act.y]!=0)
        {
            if(prevGrid[act.x][act.y]==1)
                act = Direction.RIGHT.getNext(act);
            else if(prevGrid[act.x][act.y]==2)
                act = Direction.LEFT.getNext(act);
            else if(prevGrid[act.x][act.y]==3)
                act = Direction.UP.getNext(act);
            else
                act = Direction.DOWN.getNext(act);
            grid[act.x][act.y]=c;
        }
    }
    private static class Node implements Comparable<Node>{
        final int x;
        final int y;
        final int l;
        @SuppressWarnings("unused")
        Node(){
            x=0;
            y=0;
            l=0;
        }
        Node(int x,int y,int l)
        {
            this.x=x;
            this.y=y;
            this.l=l;
        }
        @SuppressWarnings("unused")
        Node(Node n){
            x=n.x;
            y=n.y;
            l=n.l;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.l, o.l);
        }
    }
    private static  boolean check(int[][] grid,int width,int height,int x,int y,int c) {
        if(x<0 || x >= width)
            return true;
        if(y<0 || y>=height)
            return true;
        else
            return grid[x][y]!=c;
    }
    static boolean adjacent(Point a,Point b) {
        if(a.x==b.x && Math.abs(a.y-b.y)==1)
            return true;
        return a.y == b.y && Math.abs(a.x - b.x) == 1;
    }
}
