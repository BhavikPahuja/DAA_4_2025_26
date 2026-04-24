# Experiment 8

## A. Experimental Procedure

### Step 1: Implementation

- **Binary Search**
- **Subset Sum** (Verification and Decision versions)
- **Traveling Salesman Problem** (Brute Force)

The implementation should ensure correctness, proper handling of edge cases, and readability.

### Step 2: Measure Metrics

Record key performance metrics for each execution, including execution time, approximate number of operations, and feasibility status (Completed or Timeout). Maintain a structured log for further analysis.

---

## B. Analysis Tasks (Critical Thinking)

### 1. Efficiency of Binary Search

**Why does Binary Search consistently demonstrate efficient performance across large input sizes?**
Binary Search follows a "divide and conquer" approach. By repeatedly halving the search interval, it reduces the number of elements to check at an exponential rate. Its time complexity is $O(\log n)$, meaning even for a search space of billions of elements, it only requires a few dozen comparisons.

### 2. Subset Sum Complexity

**Explain why Subset Sum is computationally difficult to solve but relatively easy to verify.**

- **Solving:** To find a subset that sums to a target, one might potentially have to check all possible subsets. For a set of size $n$, there are $2^n$ subsets, leading to exponential time complexity.
- **Verifying:** Given a specific subset, one only needs to add the numbers together and compare the result to the target. This summation takes $O(n)$ time, which is polynomial and efficient.

### 3. TSP Infeasibility

**Identify the input size at which the Traveling Salesman Problem becomes infeasible and justify the reason.**
Using a Brute Force approach, TSP becomes computationally infeasible at relatively small input sizes, typically around $n = 12$ to $15$ cities. This is because the complexity is $O(n!)$. Since factorial growth ($n!$) is even faster than exponential growth, the number of permutations to check reaches trillions very quickly, exceeding the processing power of standard hardware within a reasonable timeframe.

### 4. Solving vs. Verifying

**Differentiate between solving a problem and verifying a given solution with appropriate examples.**

- **Solving** involves finding the correct answer from scratch among many possibilities (e.g., finding the prime factors of a very large number).
- **Verifying** involves taking a provided answer and checking if it meets the problem's criteria (e.g., multiplying two given numbers to see if they result in the original large number).

### 5. NP-Complete Class

**Discuss why NP-Complete problems are considered the most challenging class within NP.**
NP-Complete problems are the hardest because they act as a "universal" representative for the entire NP class. If a polynomial-time algorithm is ever found to solve any one NP-Complete problem, then every single problem in NP can also be solved in polynomial time. They represent the boundary where efficient solutions are currently unknown.
