FROM node:20 AS build

WORKDIR /app
COPY HuellasdeAsturias/package*.json ./
RUN npm install
COPY HuellasdeAsturias/ .
RUN npm run build -- --configuration production

FROM nginx:alpine
COPY --from=build /app/dist/HuellasdeAsturias/browser /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80