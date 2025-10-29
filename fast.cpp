#include <iostream>
#include <vector>
#include <numeric>
#include <algorithm>

using namespace std;

int solve()
{
    int n;
    cin >> n;
    vector<int> a(n);
    for (auto &i : a)
    {
        cin >> i;
    }

    vector<int> prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47};

    int ans = 1;
    for (auto &p : prime)
    {
        for (auto &i : a)
        {
            if (i % p)
            {
                ans = p;
                break;
            }
        }
        if (ans > 1)
        {
            break;
        }
    }
    return ans;
}

int main()
{
    // Fast I/O
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    cin >> t;
    while (t--)
    {
        cout << solve() << "\n";
    }
    return 0;
}