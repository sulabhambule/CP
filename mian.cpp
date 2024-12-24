#include <bits/stdc++.h>
using namespace std;

struct Person
{
    long long arrival, departure;
    int index;

    Person(long long a, long long d, int i) : arrival(a), departure(d), index(i) {}
};

struct Room
{
    int roomNumber;
    long long departure;

    Room(int r, long long d) : roomNumber(r), departure(d) {}
};

struct CompareDeparture
{
    bool operator()(const Room &r1, const Room &r2)
    {
        return r1.departure > r2.departure; // Min-heap based on departure time
    }
};

void solve()
{
    int n;
    cin >> n;
    vector<Person> persons;

    for (int i = 0; i < n; i++)
    {
        long long a, b;
        cin >> a >> b;
        persons.emplace_back(a, b, i);
    }

    // Sort persons by arrival time
    sort(persons.begin(), persons.end(), [](const Person &p1, const Person &p2)
         { return p1.arrival < p2.arrival; });

    priority_queue<Room, vector<Room>, CompareDeparture> pq;
    vector<int> roomAssignments(n);
    int totalRooms = 0;

    for (const auto &p : persons)
    {
        if (!pq.empty() && pq.top().departure < p.arrival)
        {
            Room freeRoom = pq.top();
            pq.pop();
            freeRoom.departure = p.departure;
            roomAssignments[p.index] = freeRoom.roomNumber;
            pq.push(freeRoom);
        }
        else
        {
            totalRooms++;
            Room newRoom(totalRooms, p.departure);
            roomAssignments[p.index] = totalRooms;
            pq.push(newRoom);
        }
    }

    cout << totalRooms << "\n";
    for (int room : roomAssignments)
    {
        cout << room << " ";
    }
    cout << "\n";
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int testCases = 1;
    // Uncomment if multiple test cases are needed
    // cin >> testCases;
    while (testCases--)
    {
        solve();
    }

    return 0;
}
