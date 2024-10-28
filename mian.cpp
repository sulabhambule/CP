#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

// Author: Sulabh Ambule
long long calculateK(const vector<int> &p)
{
    long long k = 0;
    for (int i = 0; i < p.size(); i++)
    {
        if (i % 2 == 0)
        { // For 0-based index, even i means 1st, 3rd, 5th... (1-based)
            k |= p[i];
        }
        else
        {
            k &= p[i];
        }
    }
    return k;
}

void processTestCase(int n)
{
    vector<int> p(n);

    // Fill the permutation from 1 to n
    for (int i = 0; i < n; i++)
    {
        p[i] = i + 1;
    }

    // Calculate k for the direct order
    long long maxK = calculateK(p);
    vector<int> bestSequence = p; // Store the best sequence

    // Example of trying a rearrangement strategy
    // We will use a simple swap strategy to see if we can find a better configuration.
    // This specific case does not use permutations but simple logic.

    // Swap last two elements
    swap(p[n - 1], p[n - 2]);
    long long k = calculateK(p);
    if (k > maxK)
    {
        maxK = k;
        bestSequence = p;
    }

    swap(p[n - 1], p[n - 2]);

    swap(p[0], p[1]);
    if (k > maxK)
    {
        maxK = k;
        bestSequence = p;
    }
    swap(p[0], p[1]);
    // Output results
    cout << maxK << endl;
    for (int num : bestSequence)
    {
        cout << num << " ";
    }
    cout << endl;
}

int main()
{
    int t;
    cin >> t;
    while (t-- > 0)
    {
        int n;
        cin >> n;
        processTestCase(n);
    }
    return 0;
}
