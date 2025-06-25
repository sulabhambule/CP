#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

int main()
{
    string s;
    cin >> s;
    int n = s.size();
    vector<int> pi(n);

    for (int i = 1; i < n; ++i)
    {
        int j = pi[i - 1];
        while (j > 0 && s[i] != s[j])
        {
            j = pi[j - 1];
        }
        if (s[i] == s[j])
        {
            ++j;
        }
        pi[i] = j;
    }

    vector<int> periods;
    int k = pi[n - 1];
    while (k > 0)
    {
        periods.push_back(n - k);
        k = pi[k - 1];
    }
    periods.push_back(n);
    sort(periods.begin(), periods.end());

    for (int p : periods)
    {
        cout << p << " ";
    }
    cout << endl;

    return 0;
}
