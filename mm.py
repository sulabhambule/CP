import requests

# Replace with the list of Codeforces handles
HANDLES = ["sulabhambule", "TLAM2"]  # Add multiple handles

# Contest types to include
VALID_CONTEST_TYPES = ["Div. 2", "Educational", "Div. 3"]

def get_solved_problems(handle):
    url = f"https://codeforces.com/api/user.status?handle={handle}&from=1&count=10000"
    response = requests.get(url).json()
    solved = set()

    if response["status"] == "OK":
        for submission in response["result"]:
            if submission["verdict"] == "OK":
                solved.add((submission["problem"]["contestId"], submission["problem"]["index"]))
    
    return solved

def get_latest_contests(count=400):
    """Fetches recent contests that match Div. 2, Educational, or Div. 3"""
    url = "https://codeforces.com/api/contest.list"
    response = requests.get(url).json()

    if response["status"] != "OK":
        print("Error fetching contests!")
        return []

    contests = [contest for contest in response["result"] if contest["phase"] == "FINISHED"]

    filtered_contests = []
    for contest in contests:
        if any(tag in contest["name"] for tag in VALID_CONTEST_TYPES):
            filtered_contests.append(contest)
            if len(filtered_contests) >= count:  # Get only the latest `count` contests
                break

    return filtered_contests

def get_unsolved_1400_recent(handles):
    """Gets unsolved 1400-rated problems from Div. 2, Educational, and Div. 3 contests"""
    url = "https://codeforces.com/api/problemset.problems"
    response = requests.get(url).json()

    if response["status"] != "OK":
        print("Error fetching problemset!")
        return []

    latest_contests = get_latest_contests(400)  # Fetch last 10 contests (to get enough problems)
    latest_contest_ids = {contest["id"] for contest in latest_contests}

    all_solved = set()
    for handle in handles:
        all_solved |= get_solved_problems(handle)  # Union of solved problems for all handles

    unsolved_1400 = []
    for problem in response["result"]["problems"]:
        if ("rating" in problem and problem["rating"] == 1400 and
                "contestId" in problem and problem["contestId"] in latest_contest_ids):
            problem_id = (problem["contestId"], problem["index"])
            if problem_id not in all_solved:
                unsolved_1400.append(problem)

    return unsolved_1400

def main():
    print("\n🔹 Fetching latest upcoming contests...\n")
    upcoming_contests = get_latest_contests(5)

    if upcoming_contests:
        print("📅 **Upcoming Codeforces Contests (Div. 2, Educational, Div. 3 only):**")
        for contest in upcoming_contests:
            print(f"➡ {contest['name']} (Starts: {contest['startTimeSeconds']}) | Contest ID: {contest['id']}")
    else:
        print("No upcoming contests found.")

    print("\n🔹 Fetching unsolved 1400-rated problems from recent contests...\n")
    unsolved_1400_problems = get_unsolved_1400_recent(HANDLES)

    if not unsolved_1400_problems:
        print("🎉 No unsolved 1400-rated problems found from recent Div. 2, Educational, or Div. 3 contests!")
        return

    print(f"📝 **Unsolved 1400-rated problems from recent Div. 2, Educational, and Div. 3 contests for {', '.join(HANDLES)}:**")
    for problem in unsolved_1400_problems:
        print(f"➡ {problem['contestId']}{problem['index']} - {problem['name']} (https://codeforces.com/problemset/problem/{problem['contestId']}/{problem['index']})")

if __name__ == "__main__":
    main()
