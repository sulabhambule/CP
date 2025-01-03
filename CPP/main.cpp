#include <bits/stdc++.h>
using namespace std;

// PAIRS
void Pairs()
{
  pair<int, int> p = {1, 3};

  cout << p.first << " " << p.second << "\n";
}

void explainVector()
{
  vector<int> v;

  v.push_back(1);
  v.push_back(1);
  v.emplace_back(2);

  

  for (auto i : v)
  {
    cout << i << " ";
  }
  vector<int> vv(10, 100);
}

int main()
{
  Pairs();
  explainVector();
  return 0;
}
