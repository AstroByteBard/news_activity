FROM node:17-alpine

WORKDIR /app

COPY . .

ENV PATH /app/node_modules/.bin-$PATH

RUN npm install

EXPOSE 3000

CMD ["npm", "start"]
