#include <iostream>
#include <vector>
#include <queue>
#include <climits>
using namespace std;

// Function to implement Dijkstra's algorithm
void dijkstra(const vector<vector<pair<int, int>>>& graph, int src, int V) {
    vector<int> dist(V, INT_MAX); // Stores the minimum distance from src to each vertex
    dist[src] = 0;

    // Min-heap to select the vertex with the minimum distance
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    pq.push({0, src}); // {distance, vertex}

    while (!pq.empty()) {
        int u = pq.top().second;
        pq.pop();

        // Iterate over all adjacent vertices of u
        for (const auto& neighbor : graph[u]) {
            int v = neighbor.first;
            int weight = neighbor.second;

            // If there is a shorter path to v through u
            if (dist[u] + weight < dist[v]) {
                dist[v] = dist[u] + weight;
                pq.push({dist[v], v});
            }
        }
    }

    // Print the shortest distances from the source
cout << "Vertex\t\tDistance from Source\n";
for (int i = 0; i < V; i++)
    cout << i << "\t\t\t" << dist[i] << "\n";
}

int main() {
    cout << "Aryan Chauhan IT-B 086" << endl;
    int V, E, src;
    cout << "Enter the number of vertices: ";
    cin >> V;

    // Adjacency list to store the graph as (vertex, weight) pairs
    vector<vector<pair<int, int>>> graph(V);

    cout << "Enter the number of edges: ";
    cin >> E;
    cout << "Enter the edges with weights (u v w):\n";
    for (int i = 0; i < E; i++) {
        int u, v, w;
        cin >> u >> v >> w;
        graph[u].push_back({v, w});
        graph[v].push_back({u, w});  // For undirected graph
    }

    cout << "Enter the source vertex: ";
    cin >> src;

    // Call Dijkstra's algorithm
    dijkstra(graph, src, V);

    return 0;
}
