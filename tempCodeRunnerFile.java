for (int i = 1; i < n; i++) {
            if (a[i] < a[i - 1]) {
                if (b[0] - a[i] >= a[i - 1]) {
                    a[i] = b[0] - a[i];
                }
          