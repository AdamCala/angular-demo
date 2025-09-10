# Stage 1: Build the Angular application
FROM node:20-alpine AS builder

WORKDIR /app

# Copy package.json and package-lock.json (if it exists)
COPY package*.json ./
RUN npm install

COPY . .

# With SSR disabled in angular.json, a simple build command works.
RUN npm run build

# Stage 2: Serve the application from Nginx
FROM nginx:1.25-alpine

# Copy the built static files from the builder stage.
# The outputPath is now "dist/frontend/browser" as defined in angular.json.
COPY --from=builder /app/dist/frontend/browser /usr/share/nginx/html

# Copy the custom Nginx configuration
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Expose port 80
EXPOSE 80

