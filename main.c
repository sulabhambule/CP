#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <limits.h>

int visited[100];

struct Graph
{
  int V;
  int E;
  int** Adj;
};

struct Graph* adjMatrix()
{
  struct Graph* G = (struct Graph* )malloc(sizeof(struct Graph));

  if (!G)
  {
    printf("Memory Errro \n");
    return NULL;
  }

  G->V = 7;
  G->E = 7;

  G->Adj = (int** )malloc((G->V) * sizeof(int* ));

  for (int k = 0; k < G->V; k++)
  {
    G->Adj[k] = (int*)malloc((G->V) * sizeof(int));
  }

  for (int u = 0; u < G->V; u++)
  {
    for (int v = 0; v < G->V; v++)
    {
      G->Adj[u][v] = 0;
    }
  }

  G->Adj[0][1] = G->Adj[1][0] = 1;
  G->Adj[0][2] = G->Adj[2][0] = 1;
  G->Adj[1][3] = G->Adj[3][1] = 1;
  G->Adj[1][4] = G->Adj[4][1] = 1;
  G->Adj[1][5] = G->Adj[5][1] = 1;
  G->Adj[1][6] = G->Adj[6][1] = 1;
  G->Adj[6][2] = G->Adj[2][6] = 1;

  return G;
}

void DFS(struct Graph *G, int u)
{
  visited[u] = 1;
  printf("%d ", u);
  for (int v = 0; v < G->V; v++)
  {
    if (!visited[v] && G->Adj[u][v] == 1)
    {
      DFS(G, v);
    }
  }
}

void DFST(struct Graph *G)
{
  for (int i = 0; i < 100; i++)
  {
    visited[i] = 0;
  }

  for (int i = 0; i < G->V; i++)
  {
    if (!visited[i])
    {
      DFS(G, i);
    }
  }
}

int main()
{
  // printf("Sulabh Ambule");
  struct Graph* G;
  G = adjMatrix();
  DFST(G);
}