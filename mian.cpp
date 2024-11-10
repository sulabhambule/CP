/* || JAI SHREE RAM || */

#include <iostream>
#include <map>

using namespace std;

// Author: Sulabh Ambule

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;

    map<long long, int> tickets;

    for (int i = 0; i < n; i++)
    {
        long long price;
        cin >> price;
        tickets[price]++;
    }

    for (int i = 0; i < m; i++)
    {
        long long maxPrice;
        cin >> maxPrice;

        auto it = tickets.upper_bound(maxPrice);

        if (it == tickets.begin())
        {
            cout << -1 << "\n";
        }
        else
        {
            --it;

            cout << it->first << "\n";

            if (--(it->second) == 0)
            {
                tickets.erase(it);
            }
        }
    }

    return 0;
}

void solve()
{
    int rowss, colss;
    cin >> rowss >> colss;

    vector<string> matrix;
    for (int i = 0; i < rowss; i++)
    {
        string s;
        cin >> s;
        matrix.push_back(s);
    }

    int rows = matrix.size();
    int cols = matrix[0].size();


    int top = 0;
    int left = 0;

    int right = cols - 1;
    int bottom = rows - 1;
    vector<vector<int>> result;

    while((top <= bottom) && (left <= right)) {
        vector<int> vv;
        for(int i = left; i <= right; i++) {
            
        }
    }
}
