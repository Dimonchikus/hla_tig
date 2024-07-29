#!/bin/bash

# Define the number of requests and concurrency level
NUM_REQUESTS=400000
CONCURRENCY=30
URL="http://localhost/"

# Run ApacheBench
ab -n $NUM_REQUESTS -c $CONCURRENCY $URL
