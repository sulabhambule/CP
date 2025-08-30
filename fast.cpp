#include <bits/stdc++.h>
using namespace std;

struct FastReader
{
    static const int BUFSIZE = 1 << 16;
    int idx, size;
    char buf[BUFSIZE];

    FastReader() : idx(0), size(0) {}

    inline char getChar()
    {
        if (idx >= size)
        {
            size = (int)fread(buf, 1, BUFSIZE, stdin);
            idx = 0;
            if (size == 0)
                return EOF;
        }
        return buf[idx++];
    }

    template <typename T>
    bool readInt(T &out)
    {
        char c;
        T sign = 1;
        T num = 0;
        c = getChar();
        if (c == EOF)
            return false;
        while (c != '-' && (c < '0' || c > '9'))
        {
            c = getChar();
            if (c == EOF)
                return false;
        }
        if (c == '-')
        {
            sign = -1;
            c = getChar();
        }
        for (; c >= '0' && c <= '9'; c = getChar())
            num = num * 10 + (c - '0');
        out = num * sign;
        return true;
    }

    bool readString(string &s)
    {
        char c = getChar();
        if (c == EOF)
            return false;
        while (isspace(c))
        {
            c = getChar();
            if (c == EOF)
                return false;
        }
        s.clear();
        for (; !isspace(c) && c != EOF; c = getChar())
            s.push_back(c);
        return true;
    }
};

struct Fenwick
{
    int n;
    vector<int> bit;
    Fenwick(int n) : n(n), bit(n + 1, 0) {}
    void update(int idx, int delta)
    {
        for (; idx <= n; idx += idx & -idx)
            bit[idx] += delta;
    }
    int query(int idx)
    {
        int sum = 0;
        for (; idx > 0; idx -= idx & -idx)
            sum += bit[idx];
        return sum;
    }
    int query(int l, int r)
    {
        if (l > r)
            return 0;
        return query(r) - query(l - 1);
    }
};

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    FastReader in;
    int n, q;
    in.readInt(n);
    in.readInt(q);

    vector<long long> values;
    vector<int> salary(n + 1);

    for (int i = 1; i <= n; i++)
    {
        in.readInt(salary[i]);
        values.push_back(salary[i]);
    }

    vector<string> type(q);
    vector<int> k(q);
    vector<long long> a(q), b(q), x(q);

    for (int i = 0; i < q; i++)
    {
        string t;
        in.readString(t);
        type[i] = t;
        if (t == "!")
        {
            in.readInt(k[i]);
            in.readInt(x[i]);
            values.push_back(x[i]);
        }
        else
        {
            in.readInt(a[i]);
            in.readInt(b[i]);
            values.push_back(a[i]);
            values.push_back(b[i]);
        }
    }

    // coordinate compression
    vector<long long> sorted = values;
    sort(sorted.begin(), sorted.end());
    sorted.erase(unique(sorted.begin(), sorted.end()), sorted.end());

    auto comp = [&](long long val)
    {
        return (int)(lower_bound(sorted.begin(), sorted.end(), val) - sorted.begin()) + 1;
    };

    Fenwick fTree(sorted.size());

    for (int i = 1; i <= n; i++)
        fTree.update(comp(salary[i]), 1);

    ostringstream output;

    for (int i = 0; i < q; i++)
    {
        if (type[i] == "!")
        {
            int index = k[i];
            fTree.update(comp(salary[index]), -1);
            salary[index] = (int)x[i];
            fTree.update(comp(x[i]), +1);
        }
        else
        {
            int left = comp(a[i]);
            int right = comp(b[i]);
            output << fTree.query(left, right) << "\n";
        }
    }

    cout << output.str();
    return 0;
}
