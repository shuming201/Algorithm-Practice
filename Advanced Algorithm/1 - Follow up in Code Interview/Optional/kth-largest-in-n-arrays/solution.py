from heapq import *
class Solution:
    def kthSmallest(self, matrix: List[List[int]], k: int) -> int:
        hp = []
        if not matrix or not matrix[0]:
            return None
        m = len(matrix)
        n = len(matrix[0])
        
        for i in range(min(k, m)):           
            heappush(hp, (matrix[i][0], i, 0))
        
        numberCount, number = 0, 0
        while hp:
            number, i, j = heappop(hp)
            numberCount += 1
            if numberCount == k:
                return number
            if j+1 < n: # theres still element in the row
                heappush(hp, (matrix[i][j+1], i, j+1))
        return number
            
 # O(min(K,N)+K∗logN)  
