import java.util.*;

class Point implements Comparable<Point>{
    public int x, flag, height;
    
    public Point(int x, int flag, int height){
        this.x = x;
        this.flag = flag;
        this.height = height;
    }
    
    public int compareTo(Point p){
        if (this.x == p.x){
            return this.flag - p.flag;
        }    
        return this.x - p.x;
    }
}


public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    public ArrayList<ArrayList<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (buildings == null || buildings.length == 0) return result;
        List<Point> points = new ArrayList<>();
        Queue<Integer> pq = new PriorityQueue<>();
        for (int[] building : buildings){
            points.add(new Point(building[0], 2, building[2]));
            points.add(new Point(building[1], 1, building[2]));
        }
        Collections.sort(points);
        ArrayList<Integer> cur = null;
        for (Point p : points){
            if (cur == null && p.flag == 2){   // �¿�ʼһ��
                cur = new ArrayList<Integer>();
                cur.add(p.x);
                pq.add(-p.height);
            }
            else if (p.flag == 2){         // ����һ����ʼ
                if (cur.get(0) == p.x){        // ������ʼ���غ�
                    pq.add(-p.height);
                }
                else if (-p.height < pq.peek()){ // �µĸ߶ȱ��ϵĴ�
                    cur.add(p.x);
                    cur.add(-pq.peek());
                    insert(result, cur);
                    pq.add(-p.height);
                    cur = new ArrayList<Integer>();
                    cur.add(p.x);
                }
                else{ // �µĸ߶�<=�ϵĸ߶�
                    pq.add(-p.height);
                }
            }
            else{  // ����һ������
                if (pq.peek() < -p.height){   // ��ǰ�߶ȴ��ڽ�����
                    pq.remove(-p.height);
                }
                else{   // ��ǰ�߶ȵ��ڽ�����
                    cur.add(p.x);
                    cur.add(-pq.poll());
                    insert(result, cur);
                    if (pq.size() > 0){
                        cur = new ArrayList<Integer>();
                        cur.add(p.x);
                    }
                    else{
                        cur = null;
                    }
                }
            }
        }
        return result;
    }
    
    private void insert(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> cur){
        // �ж�Integer������ȣ�Ҫʹ��equals
        if (!cur.get(0).equals(cur.get(1))){
            if (result.size() > 0 
            && result.get(result.size() - 1).get(2).equals(cur.get(2))
            && result.get(result.size() - 1).get(1).equals(cur.get(0))){
                result.get(result.size() - 1).set(1, cur.get(1)); 
            }
            else{
                result.add(cur);
            }
        }
    }
}