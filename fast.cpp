// Ayush Chavan
#include <bits/stdc++.h>
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/tree_policy.hpp>

using namespace std;
using namespace chrono;
using namespace __gnu_pbds;

#define fastio()                      \
    ios_base::sync_with_stdio(false); \
    cin.tie(NULL);                    \
    cout.tie(NULL)
#define MOD 1000000007
#define MOD1 998244353
#define INF 1e18
#define endl "\n"
#define pb push_back
#define ppb pop_back
#define mp make_pair
#define ff first
#define ss second
#define PI 3.141592653589793238462
#define set_bits __builtin_popcountll
#define sz(x) ((int)(x).size())
#define all(x) (x).begin(), (x).end()

#ifndef ONLINE_JUDGE
#define debug(x)       \
    cerr << #x << " "; \
    _print(x);         \
    cerr << endl;
#else
#define debug(x) ;
#endif

typedef long long ll;
typedef unsigned long long ull;
typedef long double lld;
typedef tree<pair<ll, ll>, null_type, less<pair<ll, ll>>, rb_tree_tag, tree_order_statistics_node_update> pbds; // find_by_order, order_of_key

void _print(ll t) { cerr << t; }
void _print(int t) { cerr << t; }
void _print(string t) { cerr << t; }
void _print(char t) { cerr << t; }
void _print(lld t) { cerr << t; }
void _print(double t) { cerr << t; }
void _print(ull t) { cerr << t; }

template <class T, class V>
void _print(pair<T, V> p);
template <class T>
void _print(vector<T> v);
template <class T>
void _print(set<T> v);
template <class T, class V>
void _print(map<T, V> v);
template <class T>
void _print(multiset<T> v);
template <class T, class V>
void _print(pair<T, V> p)
{
    cerr << "{";
    _print(p.ff);
    cerr << ",";
    _print(p.ss);
    cerr << "}";
}
template <class T>
void _print(vector<T> v)
{
    cerr << "[ ";
    for (T i : v)
    {
        _print(i);
        cerr << " ";
    }
    cerr << "]";
}
template <class T>
void _print(set<T> v)
{
    cerr << "[ ";
    for (T i : v)
    {
        _print(i);
        cerr << " ";
    }
    cerr << "]";
}
template <class T>
void _print(multiset<T> v)
{
    cerr << "[ ";
    for (T i : v)
    {
        _print(i);
        cerr << " ";
    }
    cerr << "]";
}
template <class T, class V>
void _print(map<T, V> v)
{
    cerr << "[ ";
    for (auto i : v)
    {
        _print(i);
        cerr << " ";
    }
    cerr << "]";
}
void _print(pbds v)
{
    cerr << "[ ";
    for (auto i : v)
    {
        _print(i);
        cerr << " ";
    }
    cerr << "]";
}

mt19937 rng(chrono::steady_clock::now().time_since_epoch().count());
/*---------------------------------------------------------------------------------------------------------------------------*/
ll gcd(ll a, ll b)
{
    if (b > a)
    {
        return gcd(b, a);
    }
    if (b == 0)
    {
        return a;
    }
    return gcd(b, a % b);
}
ll expo(ll a, ll b, ll mod)
{
    ll res = 1;
    while (b > 0)
    {
        if (b & 1)
            res = (res * a) % mod;
        a = (a * a) % mod;
        b = b >> 1;
    }
    return res;
}
void extendgcd(ll a, ll b, ll *v)
{
    if (b == 0)
    {
        v[0] = 1;
        v[1] = 0;
        v[2] = a;
        return;
    }
    extendgcd(b, a % b, v);
    ll x = v[1];
    v[1] = v[0] - v[1] * (a / b);
    v[0] = x;
    return;
} // pass an arry of size1 3
ll mminv(ll a, ll b)
{
    ll arr[3];
    extendgcd(a, b, arr);
    return arr[0];
} // for non prime b
ll mminvprime(ll a, ll b) { return expo(a, b - 2, b); }
bool revsort(ll a, ll b) { return a > b; }
ll combination(ll n, ll r, ll m, ll *fact, ll *ifact)
{
    ll val1 = fact[n];
    ll val2 = ifact[n - r];
    ll val3 = ifact[r];
    return (((val1 * val2) % m) * val3) % m;
}
void google(int t) { cout << "Case #" << t << ": "; }
vector<ll> sieve(int n)
{
    int *arr = new int[n + 1]();
    vector<ll> vect;
    for (int i = 2; i <= n; i++)
        if (arr[i] == 0)
        {
            vect.push_back(i);
            for (int j = 2 * i; j <= n; j += i)
                arr[j] = 1;
        }
    return vect;
}
ll mod_add(ll a, ll b, ll m)
{
    a = a % m;
    b = b % m;
    return (((a + b) % m) + m) % m;
}
ll mod_mul(ll a, ll b, ll m)
{
    a = a % m;
    b = b % m;
    return (((a * b) % m) + m) % m;
}
ll mod_sub(ll a, ll b, ll m)
{
    a = a % m;
    b = b % m;
    return (((a - b) % m) + m) % m;
}
ll mod_div(ll a, ll b, ll m)
{
    a = a % m;
    b = b % m;
    return (mod_mul(a, mminvprime(b, m), m) + m) % m;
} // only for prime m
ll phin(ll n)
{
    ll number = n;
    if (n % 2 == 0)
    {
        number /= 2;
        while (n % 2 == 0)
            n /= 2;
    }
    for (ll i = 3; i <= sqrt(n); i += 2)
    {
        if (n % i == 0)
        {
            while (n % i == 0)
                n /= i;
            number = (number / i * (i - 1));
        }
    }
    if (n > 1)
        number = (number / n * (n - 1));
    return number;
} // O(sqrt(N))
ll getRandomNumber(ll l, ll r) { return uniform_int_distribution<ll>(l, r)(rng); }
/*--------------------------------------------------------------------------------------------------------------------------*/

vector<vector<int>> g;
vector<int> parent, depth, subtree_size, heavy_node, light_tree, head, pos, values;
int idx = 0;

// Credits to Priyansh31dec for the template
// Segment Tree with Point Updates and Range Queries
// Supports multiple Segment Trees with just a change in the Node and Update
// Very few changes required everytime
template <typename Node, typename Update>
struct SegTree
{
    vector<Node> tree;
    vector<int> arr; // type may change
    int n;
    int s;
    SegTree(int a_len, vector<int> &a)
    { // change if type updated
        arr = a;
        n = a_len;
        s = 1;
        while (s < 2 * n)
        {
            s = s << 1;
        }
        tree.resize(s);
        fill(all(tree), Node());
        build(0, n - 1, 1);
    }
    void build(int start, int end, int index) // Never change this
    {
        if (start == end)
        {
            tree[index] = Node(arr[start]);
            return;
        }
        int mid = (start + end) / 2;
        build(start, mid, 2 * index);
        build(mid + 1, end, 2 * index + 1);
        tree[index].merge(tree[2 * index], tree[2 * index + 1]);
    }
    void update(int start, int end, int index, int query_index, Update &u) // Never Change this
    {
        if (start == end)
        {
            u.apply(tree[index]);
            return;
        }
        int mid = (start + end) / 2;
        if (mid >= query_index)
            update(start, mid, 2 * index, query_index, u);
        else
            update(mid + 1, end, 2 * index + 1, query_index, u);
        tree[index].merge(tree[2 * index], tree[2 * index + 1]);
    }
    Node query(int start, int end, int index, int left, int right)
    { // Never change this
        if (start > right || end < left)
            return Node();
        if (start >= left && end <= right)
            return tree[index];
        int mid = (start + end) / 2;
        Node l, r, ans;
        l = query(start, mid, 2 * index, left, right);
        r = query(mid + 1, end, 2 * index + 1, left, right);
        ans.merge(l, r);
        return ans;
    }
    void make_update(int index, ll val)
    {                                    // pass in as many parameters as required
        Update new_update = Update(val); // may change
        update(0, n - 1, 1, index, new_update);
    }
    Node make_query(int left, int right)
    {
        return query(0, n - 1, 1, left, right);
    }
};

struct Node1
{
    int val; // may change
    Node1()
    {               // Identity element
        val = -1e9; // may change
    }
    Node1(int p1)
    {             // Actual Node
        val = p1; // may change
    }
    void merge(Node1 &l, Node1 &r)
    {
        val = max(l.val, r.val);
    }
};

struct Update1
{
    int val; // may change
    Update1(ll p1)
    {             // Actual Update
        val = p1; // may change
    }
    void apply(Node1 &a)
    {
        a.val = val;
    }
};

void dfs(int node, int par)
{
    parent[node] = par;
    depth[node] = depth[par] + 1;

    for (auto &child : g[node])
    {
        if (child == par)
        {
            continue;
        }

        dfs(child, node);
        subtree_size[node] += subtree_size[child];
        if (subtree_size[heavy_node[node]] < subtree_size[child])
        {
            heavy_node[node] = child;
        }
    }

    subtree_size[node] += 1;
}

void dfsHLD(int node, int par, int chain)
{
    head[node] = chain;
    light_tree[idx] = values[node];
    pos[node] = idx++;

    if (heavy_node[node] != 0)
    {
        dfsHLD(heavy_node[node], node, head[node]);
    }

    for (auto &child : g[node])
    {
        if (child == par || child == heavy_node[node])
        {
            continue;
        }

        dfsHLD(child, node, child);
    }
}

void solve(int tt)
{
    int n, q;
    cin >> n >> q;

    g.assign(n + 1, vector<int>());
    parent.assign(n + 1, 0);
    depth.assign(n + 1, 0);
    subtree_size.assign(n + 1, 0);
    heavy_node.assign(n + 1, 0);
    pos.assign(n + 1, 0);
    light_tree.assign(n + 1, 0);
    head.assign(n + 1, 0);
    values.assign(n + 1, 0);

    for (int i = 1; i <= n; ++i)
    {
        cin >> values[i];
    }

    for (int i = 2; i <= n; ++i)
    {
        int u, v;
        cin >> u >> v;
        g[u].push_back(v);
        g[v].push_back(u);
    }

    dfs(1, 0);
    dfsHLD(1, 0, 1);

    SegTree<Node1, Update1> sgt(n, light_tree);

    while (q--)
    {
        int type;
        cin >> type;

        if (type == 1)
        {
            int s, x;
            cin >> s >> x;
            sgt.make_update(pos[s], x);
            values[s] = x;
        }
        else
        {
            int u, v;
            cin >> u >> v;
            int ans = -1e9;
            while (head[u] != head[v])
            {
                if (depth[head[u]] < depth[head[v]])
                    swap(u, v);
                ans = max(ans, sgt.make_query(pos[head[u]], pos[u]).val);
                u = parent[head[u]];
            }
            if (depth[u] > depth[v])
                swap(u, v);
            ans = max(ans, sgt.make_query(pos[u], pos[v]).val);
            cout << ans << " ";
        }
    }
    cout << endl;
}

int main()
{
#ifndef ONLINE_JUDGE
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
#endif
    fastio();
    auto start1 = high_resolution_clock::now();
    int T = 1; // cin >> T;
    for (int i = 1; i <= T; ++i)
        solve(i);
    auto stop1 = high_resolution_clock::now();
    auto duration = duration_cast<microseconds>(stop1 - start1);
#ifndef ONLINE_JUDGE
    cerr << "Time: " << duration.count() / 1000 << endl;
#endif
}