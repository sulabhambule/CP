#include <bits/stdc++.h>
using namespace std;

const int LOG = 31;
const int MAXN = 200005;

int up[MAXN][LOG];

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, q;
    cin >> n >> q;

    for (int i = 1; i <= n; ++i)
    {
        cin >> up[i][0];
    }

    // Binary lifting preprocessing
    for (int j = 1; j < LOG; ++j)
    {
        for (int i = 1; i <= n; ++i)
        {
            up[i][j] = up[up[i][j - 1]][j - 1];
        }
    }

    while (q--)
    {
        int node;
        long long k;
        cin >> node >> k;

        for (int j = LOG - 1; j >= 0; --j)
        {
            if ((k >> j) & 1)
            {
                node = up[node][j];
            }
        }

        cout << node << '\n';
    }

    return 0;
}
