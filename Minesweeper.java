class Solution {
    int [][] dirs = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1}, {-1,1},{-1,-1}};
    int m; int n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0) return null;
        int m = board.length; int n = board[0].length;
        int i = click[0];
        int j = click[1];
        if(board[i][j]=='M'){
            board[i][j]='X';
            return board;
        }
        Queue<int []> q = new LinkedList<>();
        q.add(new int []{i,j});
        while(!q.isEmpty()){
            int [] curr = q.poll();
            int mines = getMines(board, curr[0], curr[1]);
            if(mines == 0) {
                board[curr[0]][curr[1]] = 'B';
                for(int [] dir : dirs){
                    int r = curr[0] + dir[0];
                    int c = curr[1] + dir[1];
                    if(r >= 0 && r < m && c >=0 && c<n && board[r][c] =='E'){
                        board[r][c] = 'B';
                        q.add(new int []{r,c});
                    }
                }
            } else {
                board[curr[0]][curr[1]] = (char)(mines+'0');
            }
        }
        return board;
    }
    private int getMines(char[][] board, int i, int j){
        int count = 0;
        for(int [] dir : dirs){
            int r = i + dir[0];
            int c = j + dir[1];
            if(r < 0 || r>=m || c < 0 || c >= n) continue;
            if(board[r][c] == 'M') count++;
        }
        return count;
    }
}