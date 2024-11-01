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
