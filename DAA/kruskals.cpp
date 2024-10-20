#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

// Structure to represent an edge
struct Edge {
    int u, v, weight;
};

// Function to compare two edges based on their weights
bool compare(Edge a, Edge b) {
    return a.weight < b.weight;
}

// Find the parent (or representative) of a set
int findParent(int v, vector<int>& parent) {
    if (v == parent[v]) {
        return v;
    }
    return parent[v] = findParent(parent[v], parent);  // Path compression
}

// Union of two sets by rank
void unionSets(int u, int v, vector<int>& parent, vector<int>& rank) {
    u = findParent(u, parent);
    v = findParent(v, parent);

    if (u != v) {
        if (rank[u] < rank[v]) {
            parent[u] = v;
        } else if (rank[u] > rank[v]) {
            parent[v] = u;
        } else {
            parent[v] = u;
            rank[u]++;
        }
    }
}

// Kruskal's algorithm to find the MST
void kruskalMST(int V, vector<Edge>& edges) {
    // Sort the edges by weight
    sort(edges.begin(), edges.end(), compare);

    // Initialize parent and rank arrays
    vector<int> parent(V), rank(V, 0);
    for (int i = 0; i < V; i++) {
        parent[i] = i;
    }

    // Store the MST edges
    vector<Edge> mst;
    int totalWeight = 0;

    // Process each edge in the sorted list
    for (Edge& edge : edges) {
        if (findParent(edge.u, parent) != findParent(edge.v, parent)) {
            // Add the edge to the MST
            mst.push_back(edge);
            totalWeight += edge.weight;
            // Union the sets of the two vertices
            unionSets(edge.u, edge.v, parent, rank);
        }
    }

    // Print the MST edges and total weight
    cout << "Edge \tWeight\n";
    for (Edge& edge : mst) {
        cout << edge.u << " - " << edge.v << " \t" << edge.weight << "\n";
    }
    cout << "Total weight of MST: " << totalWeight << endl;
}

int main() {
    cout << "Aryan Chauhan IT-B 086"<<endl;
    int V, E;
    cout << "Enter the number of vertices: ";
    cin >> V;

    cout << "Enter the number of edges: ";
    cin >> E;

    vector<Edge> edges(E);
    cout << "Enter the edges with weights (u v w):\n";
    for (int i = 0; i < E; i++) {
        cin >> edges[i].u >> edges[i].v >> edges[i].weight;
    }

    // Call Kruskal's algorithm
    kruskalMST(V, edges);

    return 0;
}
