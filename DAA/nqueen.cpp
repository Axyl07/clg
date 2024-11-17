#include <iostream>
#include <vector>
using namespace std;

bool isSafe(const vector<vector<int>>& board, int row, int col, int N) {
    // Check this column on the upper side
    for (int i = 0; i < row; i++) {
        if (board[i][col] == 1) {
            return false;
        }
    }

    // Check upper diagonal on the left side
    for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
        if (board[i][j] == 1) {
            return false;
        }
    }

    // Check upper diagonal on the right side
    for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
        if (board[i][j] == 1) {
            return false;
        }
    }

    return true;
}

bool solveNQUtil(vector<vector<int>>& board, int row, int N) {
    // Base case: If all queens are placed
    if (row >= N) {
        return true;
    }

    for (int col = 0; col < N; col++) {
        if (isSafe(board, row, col, N)) {
            // Place this queen
            board[row][col] = 1;

            // Recur to place the rest of the queens
            if (solveNQUtil(board, row + 1, N)) {
                return true;
            }

            // If placing queen in the current position does not lead to a solution
            board[row][col] = 0; // Backtrack
        }
    }

    return false; // Trigger backtracking
}

void solveNQ(int N) {
    vector<vector<int>> board(N, vector<int>(N, 0));

    if (!solveNQUtil(board, 0, N)) {
        cout << "Solution does not exist." << endl;
        return;
    }

    // Print the solution
    cout << "One of the solutions is:\n";
    for (const auto& row : board) {
        for (int cell : row) {
            cout << (cell ? "Q " : ". ");
        }
        cout << endl;
    }
}

int main() {
    cout << "Aryan Chauhan IT-B 086" << endl;
    int N;
    cout << "Enter the number of queens: ";
    cin >> N;

    // Solve N-Queens
    solveNQ(N);

    return 0;
}
