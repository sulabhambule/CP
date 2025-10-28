#include <iostream>
#include <vector>
#include <numeric>
#include <algorithm>

using namespace std;

// Function to solve a single test case
int solve()
{
    int n;
    long long k; // k can be up to n-1, cost can be up to n
    cin >> n >> k;

    // Use a frequency array to store counts of each number
    vector<int> count(n + 1, 0);
    for (int i = 0; i < n; ++i)
    {
        int a;
        cin >> a;
        count[a]++;
    }

    // Create a prefix sum array of the counts
    // prefix_count[i] = total number of elements with value <= i
    vector<long long> prefix_count(n + 1, 0);
    for (int i = 1; i <= n; ++i)
    {
        prefix_count[i] = prefix_count[i - 1] + count[i];
    }

    // Iterate d from n down to 1
    for (int d = n; d >= 1; --d)
    {
        // We need to find the cost for this d
        // Cost is the number of elements x such that:
        // x % d != 0 AND x < 4*d

        // We only need to check numbers up to min(n, 4*d - 1)
        long long limit = min((long long)n, 4LL * d - 1);

        // Total elements we need to consider
        long long total_to_check = prefix_count[limit];

        // Count elements that are multiples of d up to the limit
        long long saved_multiples = 0;
        for (long long m = d; m <= limit; m += d)
        {
            saved_multiples += count[m];
        }

        // The cost is the total number of elements to check,
        // minus the ones that are already multiples of d.
        long long cost = total_to_check - saved_multiples;

        // If this cost is affordable, d is our answer
        if (cost <= k)
        {
            return d;
        }
    }

    // This line should not be reached because d=1 always has cost=0
    return 1;
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