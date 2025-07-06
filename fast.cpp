#include <iostream>
#include <algorithm>
using namespace std;

void solve()
{
    int a, b, x, y;
    cin >> a >> b >> x >> y;

    int ans = 0;

    if (a == b)
    {
        cout << 0 << '\n';
        return;
    }

    if (a - b == 1 && a % 2 == 1)
    {
        cout << y << '\n';
        return;
    }

    if (a > b)
    {
        cout << -1 << '\n';
        return;
    }

    while (a < b)
    {
        if (a % 2 == 1)
        {
            ans += x;
            a++;
        }
        else
        {
            a++;
            ans += min(x, y);
        }
    }

    cout << ans << '\n';
}

int main()
{
    int t;
    cin >> t;
    while (t--)
    {
        solve();
    }
    return 0;
}
