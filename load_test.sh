#!/bin/bash

# Define the number of requests and concurrency level
NUM_REQUESTS=100000
CONCURRENCY=10
URL="http://localhost/"

# Run ApacheBench
ab -n $NUM_REQUESTS -c $CONCURRENCY $URL
