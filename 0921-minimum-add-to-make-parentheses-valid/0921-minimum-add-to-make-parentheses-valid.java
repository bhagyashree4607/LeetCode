class Solution {
    public int minAddToMakeValid(String s) {
        int openNeeded = 0;   // unmatched '(' so far
        int insertions = 0;   // ')' we must add for unmatched '('

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                openNeeded++;
            } else {
                if (openNeeded > 0) {
                    openNeeded--; // this ')' matches a pending '('
                } else {
                    insertions++; // no '(' to match, must insert one
                }
            }
        }

        return insertions + openNeeded;
    }
}