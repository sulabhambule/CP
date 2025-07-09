#include <iostream>
#include <algorithm>
using namespace std;

void solve()
{
    string s;
    getline(cin, s);
    int counter = 0;
    string ans;
    int n = s.size();
    for (int i = 0; i < n; i++)
    {
        if (s[i] == '$' || s[i] == '#' || s[i] == '*')
        {
            counter++;
            continue;
        }
        if (counter > 0 && s[i] != ' ')
        {
            counter--;
        }
        else
        {
            ans.push_back(s[i]);
        }
    }

    cout << ans;
}

int main()
{
    int t;
    cin >> t;
    cin.ignore();
    while (t--)
    {
        solve();
    }
    return 0;
}
