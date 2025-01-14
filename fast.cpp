#include <iostream>
#include <string>
using namespace std;

void solve() {
    string s;
    cin >> s;
    int n = s.size();

    for (int i = 1; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
            string a = s.substr(0, i);
            string b = s.substr(i, j - i);
            string c = s.substr(j);

            if ((a <= b && c <= b) || (b <= a && b <= c)) {
                cout << a << " " << b << " " << c << endl;
                return;
            }
        }
    }

    cout << ":(" << endl;
}

int main() {
    int t;
    cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}
