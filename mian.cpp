#include <bits/stdc++.h>
using namespace std;

int main()
{
    int N; // Number of matrices
    cin >> N;

    int r, c; // Number of rows and columns in each matrix
    cin >> r >> c;

    // 3D vector to store the matrices
    vector<vector<vector<int>>> matrices(N, vector<vector<int>>(r, vector<int>(c)));

    // Read the matrices
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < r; j++)
        {
            for (int k = 0; k < c; k++)
            {
                cin >> matrices[i][j][k];
            }
        }
    }

    // Process the remaining instructions
    int m;
    while (cin >> m)
    {
        m--; // Convert to 0-based index

        // Print the requested matrix in a single line
        for (int i = 0; i < r; i++)
        {
            for (int j = 0; j < c; j++)
            {
                cout << matrices[m][i][j] << " ";
            }
        }
        cout << "\n"; // Newline after each matrix
    }

    return 0;
}
