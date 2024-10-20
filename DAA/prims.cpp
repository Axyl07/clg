#include <iostream>
#include <vector>
#include <climits>
using namespace std;

// Function to find the vertex with the minimum key value that is not in the MST
int minKey(vector<int>& key, vector<bool>& mstSet, int V) {
    int minValue = INT_MAX, minIndex;

    for (int v = 0; v < V; v++) {
        if (!mstSet[v] && key[v] < minValue) {
            minValue = key[v];
            minIndex = v;
        }
    }
    return minIndex;
}

// Function to implement Prim's algorithm
void primMST(vector<vector<int>>& graph, int V) {
    // Arrays to store the MST, keys, and included vertices
    vector<int> parent(V, -1);  // Stores the parent of each vertex in the MST
    vector<int> key(V, INT_MAX);  // Stores the minimum weight to include a vertex
    vector<bool> mstSet(V, false);  // Keeps track of vertices included in the MST

    key[0] = 0;  // Start with the first vertex
    parent[0] = -1;  // First node has no parent

    // Build the MST
    for (int count = 0; count < V - 1; count++) {
        // Pick the minimum key vertex not yet included in MST
        int u = minKey(key, mstSet, V);
        mstSet[u] = true;  // Include the picked vertex in MST

        // Update the key values and parent index of adjacent vertices
        for (int v = 0; v < V; v++) {
            if (graph[u][v] && !mstSet[v] && graph[u][v] < key[v]) {
                parent[v] = u;
                key[v] = graph[u][v];
            }
        }
    }

    // Print the MST
    cout << "Edge \tWeight\n";
    int totalWeight = 0;
    for (int i = 1; i < V; i++) {
        cout << parent[i] << " - " << i << " \t" << graph[i][parent[i]] << "\n";
        totalWeight += graph[i][parent[i]];
    }
    cout << "Total weight of MST: " << totalWeight << endl;
}

int main() {
    cout << "Aryan Chauhan IT-B 086"<<endl;
    int V, E;
    cout << "Enter the number of vertices: ";
    cin >> V;

    // Initialize a VxV graph with all edges as 0
    vector<vector<int>> graph(V, vector<int>(V, 0));

    cout << "Enter the number of edges: ";
    cin >> E;

    cout << "Enter the edges with weights (u v w):\n";
    for (int i = 0; i < E; i++) {
        int u, v, w;
        cin >> u >> v >> w;
        graph[u][v] = w;
        graph[v][u] = w;  // Undirected graph
    }

    // Call Prim's algorithm
    primMST(graph, V);

    return 0;
}
