#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <limits.h>
#include <ctype.h>

struct Stack {
  int top;
  unsigned capacity;
  int* array;
};

struct Stack* creatStack(unsigned capacity) {
  struct Stack* stack = (struct Stack*)malloc(sizeof(struct Stack));

  if (!stack) {
    return NULL;
  }

  stack->top = -1;
  stack->capacity = capacity;
  stack->array = (int*)malloc(stack->capacity * sizeof(int));

  return stack;
}

int isEmpty(struct Stack* stack) {
  return stack->top == -1;
}

int peek(struct Stack* stack) {
  return stack->array[stack->top];
}

int pop(struct Stack* stack) {
  if (!isEmpty(stack)) {
    return stack->array[stack->top--];
  }
  return INT_MIN; // Return a sentinel value if the stack is empty
}

void push(struct Stack* stack, int value) {
  stack->array[++stack->top] = value;
}

int evaluate(char* exp) {
  struct Stack* stack = creatStack(strlen(exp));
  if (!stack) {
    printf("Stack creation failed.\n");
    return -1;
  }

  for (int i = 0; exp[i]; i++) {
    if (isdigit(exp[i])) {
      push(stack, exp[i] - '0');
    } else {
      int val1 = pop(stack);
      int val2 = pop(stack);

      switch (exp[i]) {
        case '+':
          push(stack, val2 + val1);
          break;
        case '-':
          push(stack, val2 - val1);  
          break;
        case '*':
          push(stack, val2 * val1);  
          break;
        case '/':
          push(stack, val2 / val1);
          break;
      }
    }
  }

  return pop(stack);
}

int main() {   
    char exp[] = "231*+9-";
    printf("postfix evaluation: %d\n", evaluate(exp));
    return 0;
}
