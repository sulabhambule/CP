#include <iostream>
#include <vector>
#include <unordered_set>
#include <algorithm>
using namespace std;

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int t;
    cin >> t;

    while (t--)
    {
        int n;
        cin >> n;
        vector<int> a(n);
        for (int i = 0; i < n; i++)
        {
            cin >> a[i];
        }
        vector<int> prefix(n + 1, 0);
        for (int i = 0; i < n; i++)
        {
            prefix[i + 1] = prefix[i] ^ a[i];
        }

        int ans = 0;
        for (int candidate = (1 << 8) - 1; candidate >= 0; candidate--)
        {
            unordered_set<int> s;
            bool found = false;
            for (int num : prefix)
            {
        
                if (s.find(num ^ candidate) != s.end())
                {
                    ans = max(ans, candidate);
                    found = true;
                    break;
                }
                s.insert(num);
            }
            if (found)
            {
                break;
            }
        }

        cout << ans << "\n";
    }

    return 0;
}
